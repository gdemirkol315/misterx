using UnityEngine;
using UnityEngine.InputSystem;

// Attach this Script to the Background Sprite
public class PanAndZoom : MonoBehaviour
{
    [SerializeField] private float panSpeed = 1f;
    [SerializeField] private float zoomStep = 1f;

    private float minCameraSize;
    private float maxCameraSize;
    private Rect mapBoundingBox;

    [SerializeField] private Camera cam;
    private SpriteRenderer mapSprite;

    private bool isMousePanActive = false;
    private Vector3 dragOrigin;

    private void Awake()
    {
    }

    private void Start()
    {
        mapSprite = gameObject.GetComponent<SpriteRenderer>();
        Vector3 mapSize = mapSprite.sprite.bounds.size;
        Vector3 mapPosition = mapSprite.transform.position;

        float menuHeight = 300f / Screen.height * cam.orthographicSize * 2f;
        maxCameraSize = Mathf.Min(mapSize.x / cam.aspect / 2f, mapSize.y / 2f- menuHeight);
        minCameraSize = maxCameraSize / 10f;

        mapBoundingBox = new Rect(mapPosition.x - mapSize.x / 2f, mapPosition.y - mapSize.y / 2f, mapSize.x, mapSize.y);
    }

    private void Update()
    {
        PanCamera();
    }

    private Vector3 GetWorldMousePosition()
    {
        Vector2 mousePos = Mouse.current.position.ReadValue();
        Vector3 worldPos = cam.ScreenToWorldPoint(new Vector3(mousePos.x,mousePos.y,cam.nearClipPlane));

        return worldPos;
    }

    private void OnMousePanActive(InputValue value)
    {
        isMousePanActive = value.Get<float>() != 0;
        if (isMousePanActive)
            dragOrigin = GetWorldMousePosition();
    }

    private void OnMouseZoom(InputValue value)
    {
        float zoomFactor = value.Get<float>();
        ZoomCamera(zoomFactor);
    }

    private void OnKeyboardZoom(InputValue value)
    {
        float zoomFactor = value.Get<float>();
        ZoomCamera(zoomFactor);
    }

    private void OnKeyboardPan(InputValue value)
    {
        Vector2 panDirection = value.Get<Vector2>();
        Vector3 currentPosition = GetWorldMousePosition();
        Vector3 newPosition = new Vector3(currentPosition.x + panDirection.x * panSpeed, currentPosition.y + panDirection.y * panSpeed, currentPosition.z);
        cam.transform.position = ClampCamera(newPosition);
    }

    private void ZoomCamera(float zoomFactor)
    {
        if (zoomFactor != 0)
        {
            float newSize = cam.orthographicSize + zoomStep * zoomFactor;
            cam.orthographicSize = Mathf.Clamp(newSize, minCameraSize, maxCameraSize);
            cam.transform.position = ClampCamera(cam.transform.position);
        }
    }

    private void PanCamera()
    {
        if (isMousePanActive)
        {
            Vector3 diff = dragOrigin - GetWorldMousePosition();
            Vector3 newPosition = ClampCamera(cam.transform.position + diff);
            cam.transform.position = ClampCamera(newPosition);
        }
    }

    private Vector3 ClampCamera(Vector3 targetPos)
    {
        float camHeight = cam.orthographicSize;
        float camWidth = cam.orthographicSize * cam.aspect;

        float menuHeight = 300f / Screen.height * cam.orthographicSize * 2f;

        float minX = mapBoundingBox.xMin + camWidth;
        float maxX = mapBoundingBox.xMax - camWidth;
        float newX = Mathf.Clamp(targetPos.x, minX, maxX);

        float minY = mapBoundingBox.yMin + camHeight - menuHeight;
        float maxY = mapBoundingBox.yMax - camHeight;
        float newY = Mathf.Clamp(targetPos.y, minY, maxY);

        return new Vector3(newX, newY, targetPos.z);
    }
}