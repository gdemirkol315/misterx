using UnityEngine;
using UnityEngine.InputSystem;

// Attach this Script to the Background Sprite
public class PanAndZoom : MonoBehaviour
{
    //[SerializeField] private float panSpeed = 50f;
    [SerializeField] private float zoomStep = 1f;
    private float minCameraSize;
    private float maxCameraSize;
    private float mapMinX;
    private float mapMaxX;
    private float mapMinY;
    private float mapMaxY;

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

        maxCameraSize = Mathf.Max(mapSize.x / cam.aspect / 2f, mapSize.y / 2f);
        minCameraSize = maxCameraSize / 10f;

        mapMinX = mapPosition.x - mapSize.x / 2f;
        mapMaxX = mapPosition.x + mapSize.x / 2f;
        mapMinY = mapPosition.y - mapSize.y / 2f;
        mapMaxY = mapPosition.y + mapSize.y / 2f;
    }

    private void Update()
    {
        PanCamera();
    }

    private Vector3 GetWorldMousePosition()
    {
        Vector2 mousePos = Mouse.current.position.ReadValue();
        Vector3 worldPos = cam.ScreenToWorldPoint(mousePos);

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
            cam.transform.position = ClampCamera(cam.transform.position + diff);
        }
    }

    private Vector3 ClampCamera(Vector3 targetPos)
    {
        float menuHeight = 1.5f;

        float camHeight = cam.orthographicSize;
        float camWidth = cam.orthographicSize * cam.aspect;

        float minX = mapMinX + camWidth;
        float maxX = mapMaxX - camWidth;
        float newX = Mathf.Clamp(targetPos.x, minX, maxX);

        float minY = mapMinY + camHeight - menuHeight;
        float maxY = mapMaxY - camHeight;
        float newY = Mathf.Clamp(targetPos.y, minY, maxY);

        return new Vector3(newX, newY, targetPos.z);
    }
}