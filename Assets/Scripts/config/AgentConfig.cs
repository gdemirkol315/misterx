using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[CreateAssetMenu(fileName = "AgentConfig", menuName = "ScriptableObjects/AgentConfig", order = 1)]
public class AgentConfig : ScriptableObject
{
    public class AgentVariation
    {
        public Sprite avatar { get; }
        public Color color { get; }
        public string agentName { get; }

        public AgentVariation(string agentName, string avatarIcon, Color color)
        {
            this.agentName = agentName;
            this.color = color;
            this.avatar = Resources.Load<Sprite>("Player/"+ avatarIcon);
        }
    }

    public int busTickets {get; } = 11;
    public int taxiTickets { get; } = 8;
    public int metroTickets { get; } = 4;

    public AgentVariation[] agentVariations;

    private void OnEnable()
    {
        agentVariations = new AgentVariation[] {
            new AgentVariation("Player 1", "agent_icon_female_01", new Color(0.435f, 0.427f, 0.573f)),
        new AgentVariation("Player 2", "agent_icon_female_02", new Color(0.573f, 0.427f, 0.565f)),
        new AgentVariation("Player 3", "agent_icon_female_03", new Color(0.573f, 0.435f, 0.427f)),
        new AgentVariation("Player 4", "agent_icon_female_04", new Color(0.565f, 0.573f, 0.427f)),
        new AgentVariation("Player 5", "agent_icon_female_05", new Color(0.427f, 0.565f, 0.573f)),
        new AgentVariation("Player 6", "agent_icon_female_06", new Color(0.427f, 0.573f, 0.435f)),

        new AgentVariation("Player 7", "agent_icon_male_01", new Color(0.435f, 0.427f, 0.573f)),
        new AgentVariation("Player 8", "agent_icon_male_02", new Color(0.573f, 0.427f, 0.565f)),
        new AgentVariation("Player 9", "agent_icon_male_03", new Color(0.573f, 0.435f, 0.427f)),
        new AgentVariation("Player 10", "agent_icon_male_04", new Color(0.565f, 0.573f, 0.427f)),
        new AgentVariation("Player 11", "agent_icon_male_05", new Color(0.427f, 0.565f, 0.573f)),
        new AgentVariation("Player 12", "agent_icon_male_06", new Color(0.427f, 0.573f, 0.435f))
    };
    }

}
